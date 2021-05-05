

(deffacts products 
 	(product (name USBMem) (category storage) (part-number 1234) (price 199.99))
 	(product (name Amplifier) (category electronics) (part-number 2341) (price 399.99))
 	(product (name "Rubber duck") (category mechanics) (part-number 3412) (price 99.99))
)

(deffacts customers
  (customer (customer-id 101) (name joe) (address bla bla bla) (phone 3313073905))
  (customer (customer-id 102) (name mary) (address bla bla bla) (phone 333222345))
  (customer (customer-id 103) (name bob) (address bla bla bla) (phone 331567890)) 
)  	 


(deffacts orders 
	(order (order-number 300) (customer-id 102))
	(order (order-number 301) (customer-id 103))
)

(deffacts items-list
	(line-item (order-number 300) (customer-id 102) (part-number 1234))
	(line-item (order-number 301) (customer-id 103) (part-number 2341) (quantity 10))
 
)



