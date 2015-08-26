(function() {
	var app = angular.module("mom", ['ngAnimate', 'ui.bootstrap']);

	/*app.config([ '$sceDelegateProvider', function($sceDelegateProvider) {
		$sceDelegateProvider.resourceUrlWhitelist([ 'self', '/**' ])
	} ]);*/
	
    /*app.directive('onFinishRender', function ($timeout) {
	    return {
	        restrict: 'A',
	        link: function (scope, element, attr) {
	            if (scope.$last === true) {
	                $timeout(function () {
	                    scope.$emit('ngRepeatFinished');
	                });
	            }
	        }
	    }
    });*/

	app
			.controller(
					'momController',
					[
							'$scope',
							'$http',
							function($scope, $http) {
								$scope.showOptions = true;
								$scope.showCreateNewGame = false;
								$scope.showGames = false;
								$scope.showActions = false;
								$scope.showMoveActions = false;
								
								
								// Carousel
								var slides = $scope.slides = [];
								$scope.addSlide = function() {
								//var newWidth = 600 + slides.length + 1;
								  slides.push({image: 'img/port/port1.jpg'}); 
								};
								
								function isActive(slide) {
									  return slide.active;
								};
								$scope.getActiveSlide = function () {
								    return slides.filter(function (s) { return s.active; })[0];
								};
								
								// Actions -------------------------------
								
								$scope.initAction = function() {
									$scope.showOptions = true;
									$scope.showCreateNewGame = false;
									
									var slides = $scope.slides = [];
									slides.push({image: 'img/port/port1.jpg'}); 
								}
								
								$scope.newGameAction = function() {
									$scope.showOptions = false;
									$scope.showCreateNewGame = true;
									
									var slides = $scope.slides = [];
									slides.push({image: 'img/histories/history1.jpg', title:'The fall of the Linch\'s house'});
									slides.push({image: 'img/histories/history2.jpg'});
									slides.push({image: 'img/histories/history2.jpg'});
								};
								
								$scope.createNewGameAction = function() {
									var name = $scope.gameName;
									var activeSlides = $scope.activeSlides = $scope.slides.filter(isActive)[0]; 
									
									urlEvents = "rest/games/create";
									$scope.formData = {'name':name, 'history':activeSlides.title};
									
									$http({
										method:'GET',
										url: 'rest/games/create',
										data: $.param($scope.formData)
									}).success(
									function(data) {
										//
										alert("Game created");
										$scope.listGamesAction();
									});
								};
								
								$scope.showJoinGameSetupAction = function(idGame) {
									$scope.showGames = false;
									$scope.showJoinGameSetup = true;
									
									$scope.gameSelected = idGame;
								}
								
								$scope.joinGameAction = function() {
									$scope.formData = {'idGame':'0',
											'name':$scope.playerName};
									
									$http({
										method:'POST',
										url: 'rest/player/add',
										data: $.param($scope.formData),
										headers : {
									        'Content-Type' : 'application/x-www-form-urlencoded'
									    }
									}).success(
									function(data) {
										// 
										alert("Player joined " + data);
										$scope.showGameState();
									});
									
								}
								
								$scope.showGameState = function() {
									$scope.showJoinGameSetup = false;
									$scope.showOptions = false;
									$scope.showCreateNewGame = false;
									$scope.showGames = false;
									$scope.showActions = true;
									
									
									/*$http({
										method:'POST',
										url: 'rest/player/get',
										data: $.param($scope.formData),
										headers : {
									        'Content-Type' : 'application/x-www-form-urlencoded'
									    }
									}).success(
									function(data) {
										// 
										alert("Player joined " + data);
									});*/
									
									
									
									var slides = $scope.slides = [];
									// Show room
									slides.push({image: 'img/rooms/hall_center.png', title:'Hall'});
									slides.push({image: 'img/rooms/old_paper.jpg', text:'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec,  '});
									
									
									
								}
								
								$scope.listGamesAction = function() {
									$scope.showOptions = false;
									$scope.showCreateNewGame = false;
									$scope.showGames = true;
									
									urlEvents = "rest/games/list";
									
									$scope.games = [];
									$http.get(urlEvents).success(
											function(data) {
												$scope.games = data;
											});
								}
								
								$scope.initAction();
							} ]);

	app.controller('loginController', [
			'$scope',
			function($scope) {
				$scope.getUrlVars = function getUrlVars() {
					var vars = [], hash;
					var hashes = window.location.href.slice(
							window.location.href.indexOf('?') + 1).split('&');
					for (var i = 0; i < hashes.length; i++) {
						hash = hashes[i].split('=');
						vars.push(hash[0]);
						vars[hash[0]] = hash[1];
					}
					return vars;
				};

				$scope.loginFail = false;

				if ($scope.getUrlVars()["fail"] == "true") {
					$scope.loginFail = true;
				}
			} ]);

	
	/*app.controller('carouselService', function ($scope) {
		  $scope.myInterval = 5000;
		  $scope.noWrapSlides = true;
		  var slides = $scope.slides = [];
		  $scope.addSlide = function() {
		    //var newWidth = 600 + slides.length + 1;
		    
		    slides.push({image: 'img/port/port1.jpg'}); 
		  };
		  //for (var i=0; i<slides.length; i++) {
		    $scope.addSlide();
		  //}
	});*/
	
/*	app.controller('cookieController', [ '$scope', '$cookies',
			function($scope, $cookies) {
				$scope.existsToken = false;
				var token = $cookies.token;
				if (!token) {
					$scope.existsToken = false;
				} else {
					$scope.existsToken = true;
				}
			} ]);*/
})();