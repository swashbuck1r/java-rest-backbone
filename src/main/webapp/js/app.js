// Filename: app.js
define([
  'jquery',
  'underscore',
  'backbone',
  'router',
  'collections/words'
], function($, _, Backbone, Router, Words){
  var initialize = function(){
    Words.fetch({
      success: function(){
        //HACK?:initialize the router after the initial fetch (guarantees that collection items are available for everyone downstream)
        Router.start();
      }
    });
  }
  
  return {
    initialize: initialize
  };
});
