// Filename: router.js
define([
  'jquery',
  'underscore',
  'backbone',
  'models/word',
  'collections/words'
], function($, _, Backbone, Word, wordList){
  var AppRouter = Backbone.Router.extend({
    routes: {
      // Define some URL routes
      'newWord': "newWord",
      'manageWords' : 'listWords',
      'word/:id': "wordDetail",
      // Default
      '*actions': 'listWords'
      
    },

    newWord: function(){
      require(['views/word_new'], function (CreateView) {
        new CreateView({model:new Word()}).render();
      });
    },
    
    showManage: function() {
      $("#page").unbind();
      this.navigate("manageWords", true);
    },

    listWords: function() {
      $("#page").unbind();
      require(['views/word_list'], function (ListView) {
        new ListView().render();
      });
    },
    
    start: function() {
      Backbone.history.start();
      return this;
    },

    wordDetail: function(id) {
      $("#page").unbind();
      require(['views/word_detail'], function (WordDetailView) {
        this.word = wordList.get(id);
        var wordDetailView = new WordDetailView({model:this.word});
        wordDetailView.render();
      });
    }
  });
  
  return new AppRouter();
});
