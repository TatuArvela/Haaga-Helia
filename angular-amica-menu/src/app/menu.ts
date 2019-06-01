// export interface Menu {
//   restaurant:string[],
//   components:string[]
// }

export class Menu {
    restaurantName: string;
    menusForDays: string[];
    constructor(restaurantName: string, menusForDays: string[]) {
        this.restaurantName = restaurantName;
        this.menusForDays = menusForDays;
    }
}