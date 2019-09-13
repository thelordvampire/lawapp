import { Component, OnInit } from '@angular/core';

declare const $: any;
@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  constructor() { }
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
  ]
  ngOnInit() {
  }
  onPressHashtag(hashtag, delay = 0, ) {
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
