import { Component, OnInit } from '@angular/core';
import { AppService } from 'src/app/app.service';
import { FormGroup, FormBuilder } from '@angular/forms';
@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.less']
})
export class NewsComponent implements OnInit {
  searchForm: FormGroup;
  listPost: any[] = [];
  pageNumber = 1;
  pageSize = 10;

  constructor(
    private appService: AppService,
    private fb: FormBuilder,
  ) { }

  ngOnInit() {
    this.searchForm = this.fb.group({
      sizePage: [this.pageSize],
      pageNumber:[this.pageNumber],
    });
    this.changeNumberPage();
    this.changeSizePage();
    this.search(this.pageNumber, this.pageSize);
  }
  search(number, size) {
    const data = {
      page: Number(number),
      size: Number(size),
    }
    console.log(data);
    
    this.appService.GetPaging(data).subscribe(res => {
      if (res.content.length > 0) {
        this.listPost = res.content.map(el => {
          return {
            ...el,
            image: '../../../assets/images/post-grid-1-min.jpg',
            shortContent: 'Cần dữ liệu ngắn để cho vào đây, cái dữ liệu đầy đủ ko hiển thị hết được'
          }
        });
      }
      
    })
  }
  changeSizePage() {
    this.searchForm.get('sizePage').valueChanges.subscribe(res => {
      const pageNumber = this.searchForm.get('pageNumber').value;
      this.search(pageNumber, res);
    })
  }
  changeNumberPage() {
    this.searchForm.get('pageNumber').valueChanges.subscribe(res => {
      const sizePage = this.searchForm.get('sizePage').value;
      this.search(res, sizePage);
    });
  }

  changePageNumber(name) {
    const val = this.searchForm.get('pageNumber').value;
    switch (name) {
      case 'pre':
        
        break;
      case 'preEnd':
      
        break;
      case 'next':
    
        break;
      case 'nextEnd':
  
        break;
      default:
        break;
    }
  }

}
