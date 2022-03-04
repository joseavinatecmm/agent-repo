
;;Defining a template for representing/modelling products in Jess Working Memory

(deftemplate product
  (slot part-number)
  (slot name)
  (slot category)
  (slot price)
)

;;Storing five products (or five Facts) in Jess Working Memory via deffacts function 

(deffacts products 
        (product (name "USB Memory") (category storage) (part-number 1234) (price 9.99))
        (product (name Amplifier) (category electronics) (part-number 2341) (price 399.99))
        (product (name Speakers) (category electronics) (part-number 23241) (price 19.99))
        (product (name "iPhone 7") (category smartphone) (part-number 3412) (price 99.99))
        (product (name "Samsung Edge 7") (category smartphone) (part-number 34412) (price 88.99))
)

;;Defining a template for representing/modelling cutomers in Jess Working Memory

(deftemplate customer
  (slot customer-id)
  (multislot name)
  (multislot address)
)

;;Storing three customers (or three Facts) in Jess Working Memory via deffacts function 

(deffacts customers
  (customer (customer-id 101) (name joe smith) (address bla bla bla))
  (customer (customer-id 102) (name mary) (address bla bla bla))
  (customer (customer-id 103) (name bob) (address bla bla bla))
)

