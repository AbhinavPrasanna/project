import { render, waitFor } from '@testing-library/react-native';
import HomeScreen from "../Screens/HomeScreen";
import ItemDetailsScreen from './ItemDetailsScreen';
describe('HomeScreen component tests that should pass', () => {
  it("should render the product information when api responds", async () =>{
    //const home = render(<Products/>).toJSON()
   const item =  {
      product_id: '1',
      product_name: 'SUNGLAZZEZ',
      brand: 'Bratz',
      product_price: '80'
  }
    await waitFor(async () => {render(<ItemDetailsScreen itemData={item}/>).toJSON});
    expect(item).toMatchSnapshot();
  });
  it("should render the top nav bar", async () => {
    const {getByText} = render(<HomeScreen/>);
    await waitFor(async () => {render(<HomeScreen/>)});
      expect(getByText("iWare"))
});
   it("should render multiple items",async() =>{
       const items = [
        {
          product_id: '1',
      product_name: 'SUNGLAZZEZ',
      brand: 'Bratz',
      product_price: '80'
        },
        {
          product_id: '2',
      product_name: 'Aviators',
      brand: 'Gucci',
      product_price: '300'
        }
        ,
        {
          product_id: '3',
      product_name: 'Heart Glasses',
      brand: 'Oakley',
      product_price: '120'
        }
       ];
    await waitFor(async () => {render(<HomeScreen itemData={items}/>).toJSON});
    expect(items).toMatchSnapshot()
   });
   it("should render multiple items wrong",async() =>{
    const items = [
     {
       product_id: '1',
   product_name: 'SUNGLAZZEZ',
   brand: 'Bratz',
   product_price: '80'
     },
     {
       product_id: '2',
   product_name: 'Aviators',
   brand: 'Gucci',
   product_price: '300'
     }
     ,
     {
       product_id: '3',
   product_name: 'Heart Glasses',
   brand: 'Oakley',
   product_price: '120'
     }
    ];
    const item2 = 
      {
        product_id: '5',
    product_name: 'GLAZZEZ',
    brand: 'Louis Vuitton',
    product_price: '150'
      }
 await waitFor(async () => {render(<HomeScreen itemData={items}/>).toJSON})
 expect(item2).toMatchSnapshot()
});
});