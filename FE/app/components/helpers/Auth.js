export default {
    getData: function () {
        let authToken = localStorage.getItem("authToken");
        if (!authToken) return null;

        let payload = authToken.split('.')[1];
        let decodedPayload = atob(payload);

        return JSON.parse(decodedPayload);
    },

    getEmail: function () {
        let data = this.getData();
        return data ? data.email : null;
    },

    getPermissions: function () {
        let data = this.getData();
        if (data) {
            return JSON.parse(this.getData().permissions);
        } else {
            return null;
        }
    },

    isPermitted: function (permitWith) {
        if (!permitWith)
            return true;
        if (!this.getPermissions())
            return false;

        return this.getPermissions().find(authPermission => permitWith.includes(authPermission));
    }
}