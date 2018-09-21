export class CartItem {

  quantity = 1;

  taco: any;

  constructor(taco: any) {
    this.taco = taco;
  }

  get lineTotal() {
    return this.quantity * 4.99;
  }

}
