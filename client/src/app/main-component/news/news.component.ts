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
  totalPages: any[] = [];
  isNextDisable = false;
  isPreDisable = false;

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
      this.totalPages = [];
      for(let i = 1; i <= res.totalPages; i++) {
        let obj = {value: i};
        this.totalPages.push(obj);
      }
      this.hiddenButton();
      if (res.content.length > 0) {
        this.listPost = res.content.map(el => {
          const months = new Date(el.createdDatetime).getMonth() + 1;
          const day = new Date(el.createdDatetime).getDate();
          return {
            ...el,
            image: '../../../assets/images/post-grid-1-min.jpg',
            shortContent: 'Cần dữ liệu ngắn để cho vào đây, cái dữ liệu đầy đủ ko hiển thị hết được',
            months: months,
            day: day,
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
        if (!this.isPreDisable) {
          const value = Number(val) - 1;
          this.searchForm.get('pageNumber').setValue(value);
        }
        break;
      case 'preEnd':
        if (!this.isPreDisable) {
          this.searchForm.get('pageNumber').setValue(1);
        }
        break;
      case 'next':
        if (!this.isNextDisable) {
          const value = Number(val) + 1;
          this.searchForm.get('pageNumber').setValue(value);
        }
        break;
      case 'nextEnd':
        if (!this.isNextDisable) {
          this.searchForm.get('pageNumber').setValue((this.totalPages.length - 1));
        } 
        break;
      default:
        break;
    }
    this.hiddenButton();
  }

  hiddenButton() {
    const val = this.searchForm.get('pageNumber').value;
    const index = this.totalPages.findIndex(el => el.value == val);
    if (index == 0) {
      this.isPreDisable = true;
    }
    if (index == (this.totalPages.length - 1)) {
      this.isNextDisable = true;
    }
  }

  routing(data) {
    console.log(data);
    
  }

}
