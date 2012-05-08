define([
  'jquery',
  'underscore',
  'backbone',
  'models/word'
], function($, _, Backbone, dictModel){
  var wordCollection = Backbone.Collection.extend({
    model: dictModel,
    url: "rest/words",
    initialize: function(){

    }

  });

  return new wordCollection;
});
