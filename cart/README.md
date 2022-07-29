# Cart

### Files
1. **Cart**
   - Model for a single cart: contains list of product ids and quantities
2. **ProductDTO**
   - Object to hold the project id and quantity of one element of the cart (possibly temporary file)
3. **CartDAO**
   - Data Access Object for Cart using MongoDB
4. **CartService**
   - Service middle layer that currently gets the contents of a specific cart
5. **CartController**
   - Endpoints: only one cart for MVP
6. **CartApplicationTests**
   - Testing
7. **CartApplication**

### Issues
**High Priority:** None

**Low Priority:** None

### To-Do
* Jenkins pipeline integration