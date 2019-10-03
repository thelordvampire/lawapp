import { Component, OnInit } from '@angular/core';
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
