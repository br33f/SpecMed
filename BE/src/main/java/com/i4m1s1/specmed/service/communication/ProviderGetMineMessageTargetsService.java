package com.i4m1s1.specmed.service.communication;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.core.dict.WarningMsg;
import com.i4m1s1.specmed.core.helper.AuthHelper;
import com.i4m1s1.specmed.persistence.Customer;
import com.i4m1s1.specmed.persistence.MedicalEmployee;
import com.i4m1s1.specmed.persistence.PrivateMessage;
import com.i4m1s1.specmed.persistence.User;
import com.i4m1s1.specmed.repository.PrivateMessageRepository;
import com.i4m1s1.specmed.service.common.catchs.BasicServiceCatch;
import com.i4m1s1.specmed.service.common.catchs.ListServiceCatch;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.request.ListRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import com.i4m1s1.specmed.service.common.response.ListResponse;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class ProviderGetMineMessageTargetsService extends BasicServiceCatch<Object, List<MedicalEmployee>> {

    @Autowired
    private PrivateMessageRepository privateMessageRepository;

    @Autowired
    HttpServletRequest httpRequest;

    @Override
    protected BasicResponse<List<MedicalEmployee>> provide(BasicRequest<Object> request) throws SMException {
        BasicResponse<List<MedicalEmployee>> response = new BasicResponse<>();

        User currentUser = AuthHelper.readToken(httpRequest.getHeader(AuthHelper.JWT_HEADER));

        if (currentUser == null) {
            throw new SMException("20171219124125", WarningMsg.MUST_LOGGED);
        }

        List<PrivateMessage> listOfMineMessages = privateMessageRepository.findAllByCustomer_Id(new ObjectId(currentUser.getEntityId()));

        List<MedicalEmployee> listOfTargets = listOfMineMessages.stream().map(PrivateMessage::getMedicalEmployee).filter(distinctByKey(MedicalEmployee::getId)).collect(Collectors.toList());

        response.setContent(listOfTargets);
        return response;
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
}
