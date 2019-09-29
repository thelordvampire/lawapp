import { Component, OnInit, AfterContentInit, AfterViewInit, ViewChild, ChangeDetectorRef } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import {AlertService, AuthenticationService} from 'src/app/_services';
import {PostService} from '../../../_services/post.service';
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
  // tableData: object[] = [
  //   { stt: '1', title: 'Mark', content: 'Otto', createAt: '@mdo'  },
  //   { stt: '2', title: 'Jacob', content: 'Thornton', createAt: '@fat' },
  //   { stt: '3', title: 'Larry', content: 'the Bird', createAt: '@twitter' },
  //   { stt: '4', title: 'Paul', content: 'Topolski', createAt: '@P_Topolski' },
  //   { stt: '5',  title: 'Anna', content: 'Doe', createAt: '@andy'}
  // ];

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private alertService: AlertService,
    private postService: PostService,
    private auth: AuthenticationService,
  ) {

  }

  ngOnInit() {
    this.newsForm = this.formBuilder.group({
      title: ['', Validators.required],
      shortContent: ['', Validators.required],
      content: ['', Validators.required],
      image: ['', Validators.required],
      owner: ['0', Validators.required]
    });
  }
  image;
  onFileChange(event?) {
    var file = event.target.files[0];
    var reader = new FileReader();
    reader.onloadend = () => {
      // console.log('RESULT', reader.result);
      this.image = reader.result;
    };
    reader.readAsDataURL(file);
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
    const post = this.newsForm.value;
    post.image = this.image;
    post.userId = this.auth.getCurrentUser().id;
    post.owner = !(post.owner == '0');
    // console.log(post);
    this.postService.create(post).subscribe(res => {
      console.log(res);
      this.loading = false;
    }, error => {
      this.loading = false;
    });
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
          var file = this['files'][0];
          var reader = new FileReader();
          reader.onload = function () {
            event.preventDefault();
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
      // file_picker_callback: function(cb, value, meta) {
      //   var input = document.createElement('input');
      //   input.setAttribute('type', 'file');
      //   input.setAttribute('accept', 'image/*');

      //   // Note: In modern browsers input[type="file"] is functional without
      //   // even adding it to the DOM, but that might not be the case in some older
      //   // or quirky browsers like IE, so you might want to add it to the DOM
      //   // just in case, and visually hide it. And do not forget do remove it
      //   // once you do not need it anymore.

      //   input.onchange = function() {
      //     var file = this['files'][0];
      //     var reader = new FileReader();
      //     // Note: Now we need to register the blob in TinyMCEs image blob
      //     // registry. In the next release this part hopefully won't be
      //     // necessary, as we are looking to handle it internally.
      //     var id = 'blobid' + (new Date()).getTime();
      //     var blobCache = tinymce.activeEditor.editorUpload.blobCache;
      //     var base64 = String(reader.result).split(',')[1];
      //     console.log('base64', base64);

      //     var blobInfo = blobCache.create(id, file, base64);
      //     blobCache.add(blobInfo);


      //     // call the callback and populate the Title field with the file name
      //     cb(blobInfo.blobUri(), { title: file.name });
      //   };

      //   input.click();
      // }
    }
}
