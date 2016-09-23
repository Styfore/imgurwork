// ==UserScript==
// @name        imgurwork is busy
// @namespace   imgurwork
// @include     *.reddit.com/*
// @version     1
// @grant       none
// ==/UserScript==
var domain = 'http://localhost/';

function creerLien(originalLink) {
  var splitByImgur = originalLink.split('imgur.com/');
  var retour = originalLink;
  if (splitByImgur.length == 2) {
    var splitByDot = splitByImgur[1].split('.');
    if (splitByDot.length > 0) {
      var id = splitByDot[0];
      if (id.split("/").length == 0){
        id = "i/" + id;
      }
      
      retour = domain + id;
    }
  }
  return retour;
};

$('.commentarea .usertext-body > div  a').hover(function () {
  $(this).attr('href', creerLien($(this).attr('href')));
});
