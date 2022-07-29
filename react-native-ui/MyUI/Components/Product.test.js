import { render, waitFor } from '@testing-library/react-native';
import { Component } from 'react';
import { Pressable } from 'react-native'
import Product from '../Components/Product';
describe('Product component', () =>{
    it('should render a heart button with a class of outlined', async() =>{
   const item =  {
    product_id: '1',
    product_name: 'SUNGLAZZEZ',
    brand: 'Bratz',
    product_price: '80',
    picture_id:'img'
}
//    const {getByText} = render(<Product/>);
  // Component.Product()
   await waitFor(async () => {render(<Product itemData={item}/>).toJSON});
   expect(item).toMatchSnapshot();
});
});