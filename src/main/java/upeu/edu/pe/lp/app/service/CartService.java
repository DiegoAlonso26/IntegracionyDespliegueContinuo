package upeu.edu.pe.lp.app.service;

import upeu.edu.pe.lp.app.domain.ItemCart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartService {
private List<ItemCart> itemCarts;
  private HashMap<Integer,ItemCart> itemCartHashMap;

public CartService(){
    this.itemCartHashMap = new HashMap<>();
    this.itemCarts = new ArrayList<>();
}
//metodo que agrega un nuevo producto y lo actualiza en paralelo la lista en el for
public void addItemCart(Integer idProduct, String nameProduct, Integer quantity, BigDecimal price){
   ItemCart itemCart = new ItemCart(idProduct, nameProduct, quantity, price);
   itemCartHashMap.put(itemCart.getIdProduct(), itemCart);
   fillList();
}
//metodo que calcula el total del carrito
 public BigDecimal getTotalCart(){
     BigDecimal total = BigDecimal.ZERO;
     for(ItemCart itemCart : itemCarts){
         total = total.add(itemCart.getTotalPriceItem());
     }
     return total;
     
 }
 //metodo que elimina un producto agregado
 public void removeItemCart(Integer idProduct){
     itemCartHashMap.remove(idProduct);
     fillList();
 }
 //metodo que agrega en un forEach los itemCarts
  private void fillList(){
      itemCarts.clear();
      itemCartHashMap.forEach(
              (integer,itemCart)-> itemCarts.add(itemCart)
      );
  }
  //metodo que lista el carrito al procesar la orden
  public void removeAllItemCart(){
      itemCartHashMap.clear();
      itemCarts.clear();
  }
  
  //para observar el proceso del item cart en consola
  public List<ItemCart> getItemCarts(){
      return itemCarts;
  }
}
