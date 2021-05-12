

;; Loading person's template and facts to Jess Working Memory
(batch load-persons.clp)


;; Loading person rules ...

(batch load-persons-rules.clp)

;; Displaying Jess Working Memory contents (known facts)

(printout t "Current stored facts in Jess Working Memory:" crlf)
(facts)




;; Calling run function (Jess inference engine) to query the Working Memory via the rules in load-rules.clp
;; and find out which rules fire ... displaying data

(run)


;;Erase all Facts and Rules

(clear)
