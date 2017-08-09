angular
.module('app')
.controller('instrumentsCtrl', instrumentsCtrl)
.controller('counterpartiesCtrl', counterpartiesCtrl)
.controller('dealsCtrl', dealsCtrl)

instrumentsCtrl.$inject = ['$scope','$http'];
function instrumentsCtrl($scope, $http) {
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
}


counterpartiesCtrl.$inject = ['$scope','$http'];
function counterpartiesCtrl($scope, $http) {

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
}


dealsCtrl.$inject = ['$scope','$http'];
function dealsCtrl($scope, $http) {
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
}