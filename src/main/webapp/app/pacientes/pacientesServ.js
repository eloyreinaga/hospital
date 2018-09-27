'use strict';
app.service('pacientesServ', ['$http', function ($http) {
    this.getIp = function getIp() {
        return $http({
            method: 'GET',
            url: 'api/success_app'
        });
    };

    }]);
