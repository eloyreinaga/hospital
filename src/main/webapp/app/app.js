'use strict';
let app = angular.module('app', ['ngWebSocket'])
.constant('CONFIG', {
    IP_USR:0
})
.config(['$qProvider', function ($qProvider) {
    $qProvider.errorOnUnhandledRejections(false);
}])

app.run(['CONFIG','$http', function (CONFIG,$http) {
    $http({
        method: "GET",
        url: "/hospital/api/success_app"
    }).then(function(res) {
        CONFIG.IP_USR = res.data[0];
    });
}]);






