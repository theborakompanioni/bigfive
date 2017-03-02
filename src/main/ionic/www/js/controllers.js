angular.module('starter.controllers', [])

  .controller('DashCtrl', function ($scope, Goals) {
    // With the new view caching in Ionic, Controllers are only called
    // when they are recreated or on app start, instead of every page change.
    // To listen for when this page is active (for example, to refresh data),
    // listen for the $ionicView.enter event:
    //
    //$scope.$on('$ionicView.enter', function(e) {
    //});
    $scope.goals = [];
    Goals.page(0, 10).then(function (page) {
      $scope.goals = page._embedded.goal;
    });

    $scope.remove = function (goal) {
      Goals.remove(goal);
    };
  })
  .controller('ProfileCtrl', function ($scope, Users) {

    $scope.user = null;
    Users.current().then(function (user) {
      $scope.user = user;
    });
  })

  .controller('ChatsCtrl', function ($scope, Chats) {
    // With the new view caching in Ionic, Controllers are only called
    // when they are recreated or on app start, instead of every page change.
    // To listen for when this page is active (for example, to refresh data),
    // listen for the $ionicView.enter event:
    //
    //$scope.$on('$ionicView.enter', function(e) {
    //});

    $scope.chats = Chats.all();
    $scope.remove = function (chat) {
      Chats.remove(chat);
    };
  })

  .controller('GoalsCtrl', function ($scope, Goals) {
    // With the new view caching in Ionic, Controllers are only called
    // when they are recreated or on app start, instead of every page change.
    // To listen for when this page is active (for example, to refresh data),
    // listen for the $ionicView.enter event:
    //
    //$scope.$on('$ionicView.enter', function(e) {
    //});
    $scope.goals = [];
    Goals.page(0, 10).then(function (page) {
      $scope.goals = page._embedded.goal;
    });

    $scope.remove = function (goal) {
      Goals.remove(goal);
    };
  })

  .controller('GoalDetailCtrl', function ($scope, $stateParams, Goals) {
    Goals.get($stateParams.goalId).then(function (goal) {
      $scope.goal = goal;
    })
  })

  .controller('ChatDetailCtrl', function ($scope, $stateParams, Chats) {
    $scope.chat = Chats.get($stateParams.chatId);
  })

  .controller('AccountCtrl', function ($scope) {
    $scope.settings = {
      enableFriends: true
    };
  });
