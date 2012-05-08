define([
  'jquery',
  'underscore',
  'backbone',
  'collections/words',
  'models/word',
  'text!templates/word_detail.html'
], function($, _, Backbone, wordCollection, Word, wordListTemplate){
  var WordDetailView = Backbone.View.extend({
    el: $("#page"),

    // Cache the template function for a single item.
    template: _.template(wordListTemplate),

    events: {
    },

    initialize: function() {
    },

    render: function(){ 
      $(this.el).html(this.template({
        word: this.model,
        _: _
      }));
    }
  });
  
  return WordDetailView;
});