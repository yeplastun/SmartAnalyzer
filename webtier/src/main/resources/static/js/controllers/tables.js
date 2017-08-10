angular
.module('app')
.filter('startFrom', function() {
    return function(input, start) {
        if (!input || !input.length) { return; }
        start = +start; //parse to int
        return input.slice(start);
    }
})
.controller('instrumentsCtrl', instrumentsCtrl)
.controller('counterpartiesCtrl', counterpartiesCtrl)
.controller('dealsCtrl', dealsCtrl)


instrumentsCtrl.$inject = ['$scope','$http','$filter'];
function instrumentsCtrl($scope, $http, $filter) {
	$scope.currentPage = 0;
    $scope.pageSize = 10;
    $scope.q = [];
    
    $scope.instruments = []
	$scope.instrumentform = {
			instrumentId : "",
			instrumentName : ""
		};

	getInstrumentDetails();

	function getInstrumentDetails() {
		$http({
			method : 'GET',
			url : 'instrument/all'
		}).then(function successCallback(response) {
			$scope.instruments = response.data;
		}, function errorCallback(response) {
			console.log(response.statusText);
		});
	} 
    
    
    $scope.getData = function () {
    	return $filter('filter')($scope.instruments, $scope.q)
    }
    
    $scope.numberOfPages=function(){
        return Math.ceil($scope.getData().length/$scope.pageSize);                
    }
}

counterpartiesCtrl.$inject = ['$scope','$http','$filter'];
function counterpartiesCtrl($scope, $http, $filter) {

	$scope.currentPage = 0;
    $scope.pageSize = 10;
    $scope.q = [];
	
	$scope.counterparties = [];
	$scope.counterpartyform = {
			counterpartyId : "",
			counterpartyName : ""
		};
	
	getCounterpartyDetails();
	
	function getCounterpartyDetails() {
		$http({
			method : 'GET',
			url : 'counterparty/all'
		}).then(function successCallback(response) {
			$scope.counterparties = response.data;
		}, function errorCallback(response) {
			console.log(response.statusText);
		});
	}
	
    $scope.getData = function () {
    	return $filter('filter')($scope.counterparties, $scope.q)
    }
    
    $scope.numberOfPages=function(){
        return Math.ceil($scope.getData().length/$scope.pageSize);                
    }
    
}


dealsCtrl.$inject = ['$scope','$http', '$filter'];
function dealsCtrl($scope, $http, $filter) {
	
	$scope.currentPage = 0;
    $scope.pageSize = 10;
    $scope.q = [];
	
	$scope.deals = [];
	$scope.dealform = {
			dealId : "",
			dealAmount : ""
		};
	
	getDealDetails();
	
	function getDealDetails() {
		$http({
			method : 'GET',
			url : 'deal/all'
		}).then(function successCallback(response) {
			$scope.deals = response.data;
		}, function errorCallback(response) {
			console.log(response.statusText);
		});
	}
	
	$scope.getData = function () {
    	return $filter('filter')($scope.deals, $scope.q)
    }
    
    $scope.numberOfPages=function(){
        return Math.ceil($scope.getData().length/$scope.pageSize);                
    }
	
}