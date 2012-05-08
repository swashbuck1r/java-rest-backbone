// Filename: views/home/main
define([
  'jquery',
  'underscore',
  'backbone',
  'router',
  'text!templates/word_new.html'
], function($, _, Backbone, Router, wordTemplate){
  var View = Backbone.View.extend({
    el: $("#page"),

    events: {
      "click #save_word"      : "create"
    },

    create: function() {
      var self = this;
      var msg = this.model.isNew() ? 'Successfully created!' : "Saved!";
      
      this.model.save({ word: this.$('[name=word]').val(), definition: this.$('[name=definition]').val(), type: this.$('[name=type]').val() }, {
          success: function(model, resp) {
            Router.showManage();
          },
          error: function() {
              alert("An error occurred");
          }
      });
      
      return false;
    },

    render: function(){
      this.el.html(wordTemplate);
    }
  });
  return View;
});
