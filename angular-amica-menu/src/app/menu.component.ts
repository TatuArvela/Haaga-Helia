import { Component, OnInit }   from '@angular/core';
import { ActivatedRoute }      from '@angular/router';
import { AppService }          from './app.service';
import { Title }               from '@angular/platform-browser';
import { Menu }                from './menu';

@Component({
  selector:     'my-menu',
  templateUrl:  './menu.component.html',
  styleUrls:    ['./menu.component.css'],
  providers:    [<any>[AppService]]
})

export class MenuComponent implements OnInit {
  constructor(
    private _appService: AppService,
    private titleService: Title,
    private route: ActivatedRoute
  ) {}

  private menu:          Menu = {restaurantName: "", menusForDays: []};
  private errorMessage:   any = '';

  ngOnInit() {
    console.log(this.route);
    this.route.params
      .map(params => params['id'])
      .subscribe((id) => {
        this.getMenus(id);
      });
  }

  getMenus(id:string) {
    this._appService.getData(id)
      .subscribe(
        menu => {
          this.menu = menu;
          this.titleService.setTitle('Amica ' + menu.restaurantName + ' ruokalista');
        },
        error => this.errorMessage = <any>error);
  }
}
