import { Component, OnInit, AfterViewInit, DoCheck, Output, EventEmitter } from '@angular/core';
import { first } from 'rxjs/operators';
import { AuthenticationService, UserService } from 'src/app/_services';
import { NgxCarousel } from 'ngx-carousel';
import { IMAGE } from 'src/app/share/image-share';
declare var $: any;


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

    fieldList = [
      {
        id: 1,
        fieldName: 'Tư vấn pháp luật',
      },
      {
        id: 2,
        fieldName: 'Tham gia tố tụng',
      },
      {
        id: 3,
        fieldName: 'Đại diện ngoài tố tụng cho khách hàng',
      },
      {
        id: 4,
        fieldName: 'Thực hiện các dịch vụ pháp lý khác',
      },
      {
        id: 5,
        fieldName: 'Thuế',
      },
      {
        id: 6,
        fieldName: 'Doanh nghiệp',
      },
      {
        id: 7,
        fieldName: 'Đất đai',
      },
      {
        id: 8,
        fieldName: 'Hôn nhân',
      },


    ]

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
        private userService: UserService
    ) {
        this.currentUser = this.authenticationService.currentUserValue;
    }

    ngOnInit() {
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
      
    }
     ngAfterViewInit() {
        setTimeout(() => {
            $(window).load(function(){
                $('.flexslider').flexslider({
                  animation: "slide",
                  start: function(slider){
                    $('body').removeClass('loading');
                  }
                });
              }); 
        }, 100);  
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