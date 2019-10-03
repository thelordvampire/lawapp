import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AppService } from 'src/app/app.service';
import { PostService } from 'src/app/_services/post.service';

@Component({
  selector: 'app-news-detail',
  templateUrl: './news-detail.component.html',
  styleUrls: ['./news-detail.component.scss']
})
export class NewsDetailComponent implements OnInit {
  dataPost: any = {};
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private sevice: AppService,
    private postService: PostService,
  ) { }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    this.getPost(id);
  }

  getPost(id) {
    this.sevice.GetPostId(id).subscribe(data => {
      console.log(data.updatedDatetime);
      this.dataPost = data;
    })
  }

}
