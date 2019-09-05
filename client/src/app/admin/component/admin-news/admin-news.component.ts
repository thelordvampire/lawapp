import { Component, OnInit, AfterContentInit, AfterViewInit, ViewChild, ChangeDetectorRef } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AlertService } from 'src/app/_services';
// import { MdbTablePaginationComponent, MdbTableDirective } from 'PATH-TO-MDB-ANGULAR-HERE';
import { environment } from '@environments/environment';

declare var tinymce: any;

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
  ) { 
    
  }

  ngOnInit() {
    this.newsForm = this.formBuilder.group({
      title: ['', Validators.required],
      content: ['', Validators.required],
      image: ['', Validators.required],
  });
  }
  onFileChange(event?) {
    var file = event.target.files[0];
    var reader = new FileReader();
    reader.onloadend = function() {
      console.log('RESULT', reader.result)
    }
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
    // config: any = {
    //   "editable": true,
    //   "spellcheck": true,
    //   "height": "500px",
    //   "minHeight": "500px",
    //   "width": "auto",
    //   "minWidth": "0",
    //   "translate": "yes",
    //   "enableToolbar": true,
    //   "showToolbar": true,
    //   "placeholder": "Enter text here...",
    //   // imageEndPoint:  `${environment.apiUrl}/uploadOneFile`,
    //   imageEndPoint: this.onUploadImage(this),
    //   "toolbar": [
    //       ["bold", "italic", "underline", "strikeThrough", "superscript", "subscript"],
    //       ["fontName", "fontSize", "color"],
    //       ["justifyLeft", "justifyCenter", "justifyRight", "justifyFull", "indent", "outdent"],
    //       ["cut", "copy", "delete", "removeFormat", "undo", "redo"],
    //       ["paragraph", "blockquote", "removeBlockquote", "horizontalLine", "orderedList", "unorderedList"],
    //       ["link", "unlink", "image", "video"]
    //   ]
    // };
    config: any = {
      height: 'auto',
      minHeight: 450,
      theme: 'modern',
      plugins: 'print preview fullpage searchreplace autolink directionality visualblocks visualchars fullscreen image imagetools link media template codesample table charmap hr pagebreak nonbreaking anchor insertdatetime advlist lists textcolor wordcount contextmenu colorpicker textpattern autoresize',
      toolbar: 'formatselect | bold italic strikethrough forecolor backcolor | link | alignleft aligncenter alignright alignjustify  | numlist bullist outdent indent  | removeformat | link image',
      image_advtab: true,
      imagetools_toolbar: 'rotateleft rotateright | flipv fliph | editimage imageoptions',
      templates: [
        { title: 'Test template 1', content: 'Test 1' },
        { title: 'Test template 2', content: 'Test 2' }
      ],
      content_css: [
        // '//fonts.googleapis.com/css?family=Lato:300,300i,400,400i',
        // '//www.tinymce.com/css/codepen.min.css'
      ],
      file_picker_types: 'image',
      file_picker_callback: function (cb, value, meta) {
        var input = document.createElement('input');
        input.setAttribute('type', 'file');
        input.setAttribute('accept', 'image/*');
        input.onchange = function () {
          var file = this.files[0];
          var reader = new FileReader();
          reader.onload = function () {
            var id = 'blobid' + (new Date()).getTime();
            var blobCache =  tinymce.activeEditor.editorUpload.blobCache;
            var base64 = String(reader.result).split(',')[1];
            var blobInfo = blobCache.create(id, file, base64);
            blobCache.add(blobInfo);

            cb(blobInfo.blobUri(), { title: file.name });
          };
          reader.readAsDataURL(file);
        };
    
        input.click();
      }
    }
}
