import { Component, OnInit, AfterContentInit, AfterViewInit, ViewChild, ChangeDetectorRef } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AlertService } from 'src/app/_services';
// import { MdbTablePaginationComponent, MdbTableDirective } from 'PATH-TO-MDB-ANGULAR-HERE';
import { environment } from '@environments/environment';
@Component({
  selector: 'app-admin-news',
  templateUrl: './admin-news.component.html',
  styleUrls: ['./admin-news.component.scss']
})
export class AdminNewsComponent implements OnInit  {
  newsForm: FormGroup;
  submitted = false;
  loading = false;
  tableData: object[] = [
    { stt: '1', title: 'Mark', content: 'Otto', createAt: '@mdo'  },
    { stt: '2', title: 'Jacob', content: 'Thornton', createAt: '@fat' },
    { stt: '3', title: 'Larry', content: 'the Bird', createAt: '@twitter' },
    { stt: '4', title: 'Paul', content: 'Topolski', createAt: '@P_Topolski' },
    { stt: '5',  title: 'Anna', content: 'Doe', createAt: '@andy'}
  ];

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private alertService: AlertService,
  ) { }

  ngOnInit() {
    this.newsForm = this.formBuilder.group({
      title: ['', Validators.required],
      content: ['', Validators.required],
      image: ['', Validators.required],
  });
  }
  get f() { return this.newsForm.controls; }
  createNews() {
    this.submitted = true;

    // reset alerts on submit
    this.alertService.clear();

    // stop here if form is invalid
    if (this.newsForm.invalid) {
        return;
    }

    this.loading = true;
    console.log(this.newsForm)
  }
    config: any = {
      "editable": true,
      "spellcheck": true,
      "height": "500px",
      "minHeight": "500px",
      "width": "auto",
      "minWidth": "0",
      "translate": "yes",
      "enableToolbar": true,
      "showToolbar": true,
      "placeholder": "Enter text here...",
      imageEndPoint: `${environment.apiUrl}/uploadOneFile`,
      "toolbar": [
          ["bold", "italic", "underline", "strikeThrough", "superscript", "subscript"],
          ["fontName", "fontSize", "color"],
          ["justifyLeft", "justifyCenter", "justifyRight", "justifyFull", "indent", "outdent"],
          ["cut", "copy", "delete", "removeFormat", "undo", "redo"],
          ["paragraph", "blockquote", "removeBlockquote", "horizontalLine", "orderedList", "unorderedList"],
          ["link", "unlink", "image", "video"]
      ]
    };
}
