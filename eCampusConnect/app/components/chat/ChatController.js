ChatModule.controller('ChatController', function($scope,$rootScope,ChatService) {
  $scope.messages = [];
  //$scope.message ={};
  $scope.max = 140;

  $scope.addMessage = function() {
  	$scope.message.userid=$rootScope.currentUser.userName;
    ChatService.send($scope.message);
    $scope.message.message="";
  };

  ChatService.receive().then(null, null, function(message) {
    $scope.messages.push(message);
    console.log("receive method");
    console.log($scope.messages);
  });
});