angular.module('starter.services', [])

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


  .factory('Goals', function () {
    // Might use a resource here that returns a JSON array

    // Some fake testing data
    var goals = [{
      id: 0,
      name: 'Ben Sparrow',
      description: 'You on your way?',
      img: 'img/ben.png'
    }, {
      id: 1,
      name: 'Max Lynx',
      description: 'Hey, it\'s me',
      img: 'img/max.png'
    }, {
      id: 2,
      name: 'Adam Bradleyson',
      description: 'I should buy a boat',
      img: 'img/adam.jpg'
    }, {
      id: 3,
      name: 'Perry Governor',
      description: 'Look at my mukluks!',
      img: 'img/perry.png'
    }, {
      id: 4,
      name: 'Mike Harrington',
      description: 'This is wicked good ice cream.',
      img: 'img/mike.png'
    }];

    return {
      all: function () {
        return goals;
      },
      remove: function (chat) {
        goals.splice(goals.indexOf(chat), 1);
      },
      get: function (goalId) {
        for (var i = 0; i < goals.length; i++) {
          if (goals[i].id === parseInt(goalId)) {
            return goals[i];
          }
        }
        return null;
      }
    };
  });
