angular.module('starter.services', ['restangular'])

  .factory('Chats', function () {
    // Might use a resource here that returns a JSON array

    // Some fake testing data
    var chats = [{
      id: 0,
      name: 'Ben Sparrow',
      lastText: 'You on your way?',
      face: 'img/ben.png'
    }, {
      id: 1,
      name: 'Max Lynx',
      lastText: 'Hey, it\'s me',
      face: 'img/max.png'
    }, {
      id: 2,
      name: 'Adam Bradleyson',
      lastText: 'I should buy a boat',
      face: 'img/adam.jpg'
    }, {
      id: 3,
      name: 'Perry Governor',
      lastText: 'Look at my mukluks!',
      face: 'img/perry.png'
    }, {
      id: 4,
      name: 'Mike Harrington',
      lastText: 'This is wicked good ice cream.',
      face: 'img/mike.png'
    }];

    return {
      all: function () {
        return chats;
      },
      remove: function (chat) {
        chats.splice(chats.indexOf(chat), 1);
      },
      get: function (chatId) {
        for (var i = 0; i < chats.length; i++) {
          if (chats[i].id === parseInt(chatId)) {
            return chats[i];
          }
        }
        return null;
      }
    };
  })


  .factory('Goals', ['Restangular', function (Restangular) {
    return {
      page: function (page, size) {
        return Restangular.one('goal').get({
          page: page >= 0 ? page : 0,
          size: size >= 0 ? size : 10
        });
      },
      get: function (goalId) {
        return Restangular.one('goal', goalId).get();
      }
    };
  }])
  .factory('Users', ['Restangular', function (Restangular) {
    return {
      page: function (page, size) {
        return Restangular.one('user').get({
          page: page >= 0 ? page : 0,
          size: size >= 0 ? size : 10
        });
      },
      get: function (userId) {
        return Restangular.one('user', userId).get()
          .then(function (response) {
            return response._embedded.user;
          });
      },
      findByName: function (name) {
        return Restangular.oneUrl('user', '/user/search/findByName?name=' + name).get()
      },
      current: function () {
        return this.findByName('demo').then(function (response) {
          return response._embedded.user[0];
        });
      }
    };
  }]);
