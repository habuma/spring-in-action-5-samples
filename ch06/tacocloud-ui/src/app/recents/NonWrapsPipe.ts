import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'nonwraps'
})

export class NonWrapsPipe implements PipeTransform {
  transform(ingredients: any, ...args: any[]): any {
    const nonwraps = [];
    for (const ingredient of ingredients) {
      if (ingredient.type !== 'WRAP') {
        nonwraps.push(ingredient);
      }
    }
    return nonwraps;
  }
}
