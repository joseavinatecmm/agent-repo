
;;Loading products and customer templates and facts to Jess Working Memory

(load "load-prod-cust.clp") 

;;Loading products and customer rules 

(load "load-prodcust-rules.clp")

;;Displaying Jess Working Memory known facts (contents) about products and customers

(printout t "Current stored facts in CLIPS Working Memory:" crlf)
(facts)

;; Call run function (Jess inference engine) to query the Jess Working Memory
;; and find out which rules fire ... displaying data

(run)

;; Call clear function to erase all Facts and Rules

(clear)  

