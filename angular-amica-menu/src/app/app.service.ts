import { Injectable }     from "@angular/core";
import { Http, Response } from '@angular/http';
import { Observable }     from 'rxjs/Rx';
import { Menu }           from './menu';

@Injectable()
export class AppService {
    constructor(private http:Http) {
    }
    
    getData(id:string):Observable<Menu> {
        // TODO: Select restaurant and time period
        console.log('http://localhost:4000/' + id);
        return this.http.get('http://localhost:4000/' + id)
        .map(this.extractData)
        .catch(this.handleError);
    }
    
    private extractData(res:Response):Menu {
        let body = res.json();
        let rawMenusForDays = body.MenusForDays;
        
        let restaurantName = body.RestaurantName;
        let menusForDays: string[] = [];
        
        for (let i in rawMenusForDays) {
            if(rawMenusForDays[i].SetMenus.length > 0) {
                let newMenuForDay: any = {};
                newMenuForDay.setMenus = [];
                newMenuForDay.date = rawMenusForDays[i].Date;
                
                for (let j in rawMenusForDays[i].SetMenus) {
                    let newSetMenu: any = {};
                    newSetMenu.components = [];
                    
                    let name = rawMenusForDays[i].SetMenus[j].Name || "Linjasto";
                    newSetMenu.name = name.charAt(0).toUpperCase() + name.substr(1).toLowerCase();
                    
                    for (let k in rawMenusForDays[i].SetMenus[j].Components) {
                        
                        if(rawMenusForDays[i].SetMenus[j].Components[k])
                        newSetMenu.components[k] = rawMenusForDays[i].SetMenus[j].Components[k]
                        // Remove allergy information
                        .replace(/ *\([^)]*\) */g, "")
                        // Fix comma spacing
                        .replace(/,[s]*/g, ", ");
                    }
                    
                    if(newSetMenu.components.length > 0)
                    newMenuForDay.setMenus[j] = newSetMenu;
                }
                
                menusForDays[i] = newMenuForDay;
            }
        }
        
        let menu = new Menu(restaurantName, menusForDays);
        
        return menu;
    }
    
    private handleError(error:any) {
        let errMsg = (error.message) ? error.message :
        error.status ? `${error.status} - ${error.statusText}` : 'Server error';
        console.error(errMsg);
        return Observable.throw(errMsg);
    }
}