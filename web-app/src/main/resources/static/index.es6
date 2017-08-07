const app = angular.module('postserviceApp', []);
app.controller('postserviceCtrl', function ($scope, $http) {
    $scope.name = null;
    $scope.age = null;
    $scope.adress = null;
    $scope.lblMsg = null;
    $scope.postdata = function (name, age, adress) {
        let data = {
            name: name,
            age: age,
            adress: adress
        };
        $http.post('index.jsp', JSON.stringify(data)).then(function (response) {
            if (response.data)
                $scope.msg = response.data;
        }, function (response) {
            $scope.msg = "Service not Exists";
            $scope.statusval = response.status;
            $scope.statustext = response.statusText;
            $scope.headers = response.headers();
        });
    };
});
