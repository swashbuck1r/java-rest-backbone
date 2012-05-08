define([
  'underscore',
  'backbone'
], function(_, Backbone) {

  //put interesting things in the model, even validation
  var Words = Backbone.Model.extend({
    initialize: function(){
    },
    url : function() {
      var base = 'rest/words';
      if (this.isNew()) return base;
      return base + (base.charAt(base.length - 1) == '/' ? '' : '/') + this.id;
    }
  });
  return Words;

});