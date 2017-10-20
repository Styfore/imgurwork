// ==UserScript==
// @name        imgurwork is busy
// @namespace   imgurwork
// @include     *.reddit.com/*
// @version     1
// @grant       none
// ==/UserScript==
var domain = 'http://imwork.styfore.ovh/';

function creerLien(originalLink) {
  var splitByImgur = originalLink.split('imgur.com/');
  var retour = originalLink;
  console.info(splitByImgur);
  if (splitByImgur.length == 2) {
    var splitByDot = splitByImgur[1].split('.');
    if (splitByDot.length > 0) {
      console.info(splitByDot);
      var id = splitByDot[0];
      console.info(id);
      if (id.split("/").length == 1){
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

$('div.link div.entry a.title').hover(function () {
  $(this).attr('href', creerLien($(this).attr('href')));
  $(this).attr('data-href-url', creerLien($(this).attr('data-href-url')));
  $(this).removeAttr("data-outbound-expiration");
  $(this).removeAttr("data-outbound-url");
  
});

$('#siteTable div.entry div.expando a').hover(function () {
  $(this).attr('href', creerLien($(this).attr('href')));
});
