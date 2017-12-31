const LOGGED = "LOGGED";
const NOT_LOGGED = "NOT_LOGGED";

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

    isLogged: function () {
        return this.getData();
    },

    isPermitted: function (permitWith) {
        if (!permitWith)
            return true;

        let permissionsToCheck = [];
        if (this.getPermissions()) {
            permissionsToCheck = [...permissionsToCheck, ...this.getPermissions()];
        }

        if (this.isLogged()) {
            permissionsToCheck = [...permissionsToCheck, LOGGED];
        } else {
            permissionsToCheck = [...permissionsToCheck, NOT_LOGGED];
        }

        return permissionsToCheck.find(authPermission => permitWith.includes(authPermission));
    }
}