define([
  'jquery',
  'underscore',
  'backbone',
  'collections/words',
  'text!templates/word_list.html'
], function($, _, Backbone, wordCollection, wordListTemplate){
  var View = Backbone.View.extend({
    el: $("#page"),

    // Cache the template function for a single item.
    template: _.template(wordListTemplate),

    events: {
      "click #create_word"      : "create"
    },

    create: function() {
      alert("hey there" + this.collection.get("cat").get("type"));
    },

    initialize: function() {
      //some defaults to start with
      this.collection = wordCollection;
      _.bindAll(this, "render");
      _.bindAll(this, "fetchAndRender");
      this.collection.bind('change', this.fetchAndRender);
      this.collection.bind('add', this.fetchAndRender);
      this.collection.bind('remove', this.fetchAndRender);
    },
    
    fetchAndRender: function(){ 
      alert("fetch and render");
      wordCollection.fetch({success: function(){this.render();}})
    },
    
    render: function(){ 
      
      var view = this;
      $(view.el).html("loading...");  

      this.collection.fetch()
      .done(function(data){
        $(view.el).html(view.template({
            words: view.collection.models,
            _: _
         }));    
      });
	  }

  });
  return View;
});
