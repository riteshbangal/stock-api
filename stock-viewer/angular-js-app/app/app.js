var app = angular.module('myApp', []);

app.config(['$httpProvider', function($httpProvider) {
    $httpProvider.defaults.timeout = 20000;
}])

app.controller('myCtrl', function($scope, $http) {
    
    this.retrieve = function() {
        
        const url = "http://127.0.0.1:7002/api/db-service/data/rcb";
        //const url = "http://127.0.0.1:7002/api/stock-service/stock/" + $scope.name;

        // Create an empty Headers instance
        var request = new Request(url, {
            method: 'GET',
            mode: 'no-cors',
            headers: new Headers({
                //'Content-Type': 'application/json'
                'Content-Type': 'text/plain'
            })
        });

        //$http.get(url, {mode: 'cors', method: 'get'})
        fetch(request)
        .then(function (response) {
            console.log('inside' + response);
            $scope.quotes = response.data;
        }, function (response) {
            console.log('came here');
        });
    }

    this.add = function() {
        var message = {
            userName: $scope.name,
            quotes: [$scope.quote]
        }
        $http.post('http://127.0.0.1:7002/api/stock-service/stock/add', message)
            .then(function(response) {
                $scope.quotes = response.data;
            }, function(response) {
                console.log('error..');
            });
    }
});
