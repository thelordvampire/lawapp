import { Component, OnInit } from '@angular/core';
import * as $ from 'jquery';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  isShowResources = false;
  constructor() {
   }

  ngOnInit() {
    $(window).on('load', function() {
      smallNavFunctionality();
  });
   $(window).on("resize", function() {
        toggleClassForSmallNav();
        // smallNavFunctionality();

        clearTimeout($.data(this, 'resizeTimer'));
        $.data(this, 'resizeTimer', setTimeout(function() {
            smallNavFunctionality();
        }, 200));
    });
    $("span.menu").click(function(){
      $(" ul.navig").slideToggle("slow" , function(){
      });
    });
        
    if ($(".header-search-area").length) {
      console.log('sadassdsdas');
      var serachFormBox = $(".header-search-area .header-search-form");
      var openSeachBtn = $(".header-search-area .open-btn");

      $(document.body).append(serachFormBox);
      serachFormBox.hide();

      openSeachBtn.on("click", function(e) {
          serachFormBox.fadeIn();
          return false;
      });

      serachFormBox.on("click", function() {
          serachFormBox.fadeOut();
          return false;
      }).find(".form").on("click", function(e) {
          e.stopPropagation();
      })
    }

    function smallNavFunctionality() {
      var windowWidth = window.innerWidth;
      var mainNav = $(".navigation-holder");
      var smallNav = $(".navigation-holder > .small-nav");
      var subMenu = smallNav.find(".sub-menu");
      var megamenu = smallNav.find(".mega-menu");
      var menuItemWidthSubMenu = smallNav.find(".menu-item-has-children > a");

      if (windowWidth <= 991) {
          subMenu.hide();
          megamenu.hide();
          menuItemWidthSubMenu.on("click", function(e) {
              var $this = $(this);
              $this.siblings().slideToggle();
               e.preventDefault();
              e.stopImmediatePropagation();
          })
      } else if (windowWidth > 991) {
          mainNav.find(".sub-menu").show();
          mainNav.find(".mega-menu").show();
      }
  }

  smallNavFunctionality();

  function toggleClassForSmallNav() {
    var windowWidth = window.innerWidth;
    var mainNav = $("#navbar > ul");

    if (windowWidth <= 991) {
        mainNav.addClass("small-nav");
    } else {
        mainNav.removeClass("small-nav");
    }
}

toggleClassForSmallNav();
  }
}
