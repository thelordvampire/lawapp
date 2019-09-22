import { Component, OnInit } from '@angular/core';
import { log } from 'util';
import {UserService} from '../../_services';
import {ActivatedRoute} from '@angular/router';

declare const $: any;
@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
  ) { }

  inforDetail = [
    {
      certificate: 'Khóa đào tạo kỹ năng hành nghề Luật sư',
      placeOfIssue: 'Học viện tư pháp Việt Nam',
      yearOfIssue: '2001',
    },
    {
      certificate: 'Chứng chỉ hành nghề Luật sư',
      placeOfIssue: 'Bộ Tư pháp',
      yearOfIssue: '2005',
    },
    {
      certificate: 'Thẻ Luật sư',
      placeOfIssue: 'Liên đoàn Luật sư Việt Nam',
      yearOfIssue: '2002',
    },
  ]
  prize = [
    {
      id: 1,
      name: 'đào tạo kỹ năng hành nghề Luật sư'
    },
    {
      id: 2,
      name: 'đào tạo kỹ năng hành nghề Luật sư'
    }
  ];

  user;
  userId;
  ngOnInit() {
    if (this.route.snapshot && this.route.snapshot.params.id) {
      this.userId = this.route.snapshot.params.id;
      this.userService.getById(this.userId).subscribe(res => {
        this.user = res;
      });
    }
  }

  onPressHashtag(hashtag, delay = 0, ) {
    const elem = document.querySelectorAll('[title*="'+hashtag+'"]');
    $('.form-right-menu div').removeClass('active');
    $(elem).addClass('active');
    if (!$(hashtag).length) {
      return;
    }

    setTimeout(() => {
      if (!$(hashtag).offset()) {
        return;
      }
      $('html, body').animate({
        scrollTop: $(hashtag).offset().top
      }, 800, function () { });
    }, delay);
  }
}
