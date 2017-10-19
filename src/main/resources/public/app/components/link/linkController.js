'use_strict';
(function(){
	angular.module("imgurwork").controller("linkController", ["$scope", "$window", function($scope, $window){
		$scope.link = "";
		
		$scope.toLink = function(){
			var errorMessage = "It is not a imgur link (or maybe my app is dumb)";
			$scope.errorMessage = "";
			var splitByImgur = $scope.imgurLink.split("imgur.com/");
			if (splitByImgur.length == 2){
				var splitBySlash = splitByImgur[1].split("/");
				var splitByDot = splitByImgur[1].split(".");
				if (splitBySlash.length > 0 && splitBySlash[0] == "a"){
					var galleryId = splitBySlash[1];
					$window.location.assign("/gallery/" + galleryId);
				}
				else if (splitBySlash.length > 0 && splitBySlash[0] == "gallery"){
					var albumId = splitBySlash[1];
					$window.location.assign("/album/" + albumId);
				}
				else if (splitByDot.length > 0){
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
