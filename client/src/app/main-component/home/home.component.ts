import { Component, OnInit, AfterViewInit, DoCheck, Output, EventEmitter } from '@angular/core';
import { AuthenticationService, UserService } from 'src/app/_services';
import { NgxCarousel } from 'ngx-carousel';
import { IMAGE } from 'src/app/share/image-share';
import {FieldService} from '../../_services/field.service';
// import * as $ from 'jquery';
declare var $ :any;


@Component({ selector: 'home',
templateUrl: 'home.component.html',
styleUrls: ['./home.component.scss'] })
export class HomeComponent implements OnInit, AfterViewInit,  DoCheck {
  @Output('currentSlide') _currentSlide: EventEmitter<any> = new EventEmitter();
  imageId: number;
    currentUser: any;
    image_icon = IMAGE;
  newsList = [
    {
    id: 1,
    title: 'Vì: Quyền, lợi ích hợp pháp của khách hàng,',
    content: 'Nên: sẵn sàng tận tâm vì khách hàng, tận tụy với công việc, hoàn thành dịch vụ đúng tiến độ, đáp ứng chính xác yêu cầu của khách hàng.'
   },
   {
    id: 2,
    title: 'Vì: Quyền, lợi ích hợp pháp của khách hàng,',
    content: 'Nên: sẵn sàng tận tâm vì khách hàng, tận tụy với công việc, hoàn thành dịch vụ đúng tiến độ, đáp ứng chính xác yêu cầu của khách hàng.'
   },
   {
    id: 3,
    title: 'Vì: Quyền, lợi ích hợp pháp của khách hàng,',
    content: 'Nên: sẵn sàng tận tâm vì khách hàng, tận tụy với công việc, hoàn thành dịch vụ đúng tiến độ, đáp ứng chính xác yêu cầu của khách hàng.'
   },
]

    users = [];

    slides = [
      {
        id: 1,
        name: 'image 1',
        src: '../assets/images/20.jpg'
      },
      {
        id: 2,
        name: 'image 2',
        src: '../assets/images/20.jpg'
      },
      {
        id: 3,
        name: 'image 3',
        src: '../assets/images/20.jpg'
      },
      {
        id: 4,
        name: 'image 4',
        src: '../assets/images/20.jpg'
      },
      {
        id: 5,
        name: 'image 5',
        src: '../assets/images/20.jpg'
      },
      {
        id: 6,
        name: 'image 4',
        src: '../assets/images/20.jpg'
      },
      {
        id: 7,
        name: 'image 5',
        src: '../assets/images/20.jpg'
      }
    ]

    listField;

    tagList = [
      {
        tagName: 'hello',
      },
      {
        tagName: 'hello2',
      },
      {
        tagName: 'hello3',
      },
      {
        tagName: 'hello4',
      },
      {
        tagName: 'hello5',
      },
      {
        tagName: 'hello5',
      },
      {
        tagName: 'hello5',
      },
      {
        tagName: 'hello5',
      },
      {
        tagName: 'hello5',
      },
      {
        tagName: 'hello5',
      },
      {
        tagName: 'hello5',
      },
    ]
    public carouselOne: NgxCarousel;
    constructor(
        private authenticationService: AuthenticationService,
        private userService: UserService,
        private fieldService: FieldService,
    ) {
        this.currentUser = this.authenticationService.currentUserValue;
    }

    ngOnInit() {
      this.fieldService.getAll().subscribe(res => {
        this.listField = res;
      });

      this.carouselOne = {
        grid: {xs: 1, sm: 1, md: 1, lg: 3, all: 0},
        slide: 1,
        speed: 400,
        interval: 10000,
        point: {
          visible: false
        },
        load: 3,
        touch: true,
        loop: true,
        custom: 'banner'
      }
      function heroSlider() {
        if ($(".hero-slider").length) {
            $(".hero-slider").slick({
                autoplay: true,
                autoplaySpeed: 8000,
                arrows: true,
                prevArrow: '<button type="button" class="slick-prev">Previous</button>',
                nextArrow: '<button type="button" class="slick-next">Next</button>',
                dots: true,
                fade: true,
                cssEase: 'linear',
            });
        }
      }
      heroSlider();
    }
     ngAfterViewInit() {

     }
     ngDoCheck(){

    }
    onPressImage(event) {
      this.imageId = event.id - 1;
    };
    onmoveFn(event) {
      this._currentSlide.emit(event.currentSlide)
    }
}
