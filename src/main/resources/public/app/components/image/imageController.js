'use_strict';
(function(){
	angular.module("imgurwork").controller("imageController", ["$scope", "$window", function($scope, $window){
		$scope.link = "";
		
		$scope.toImage = function(){
			var errorMessage = "It is not a imgur link (or maybe my app is dumb)";
			$scope.errorMessage = "";
			var splitByImgur = $scope.imageLink.split("imgur.com/");
			if (splitByImgur.length == 2){
				var splitByDot = splitByImgur[1].split(".");
				if (splitByDot.length > 0){
					var imageId = splitByDot[0];
					$window.location.assign("/image/" + imageId);
				}
				else {
					console.info(errorMessage);
					$scope.errorMessage = errorMessage;
				}
			}
			else{
				console.info(errorMessage);
				$scope.errorMessage = errorMessage;
			}
		};
		
	}]);
})();
