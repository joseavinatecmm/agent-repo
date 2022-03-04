

;; Loading person's template and facts to Jess Working Memory
(load "persons/load-persons.clp")

;; Loading person rules ...

(load "persons/load-persons-rules.clp")

;;(reset)

;; Displaying Jess Working Memory contents (known facts)

;;(printout t "Current stored facts in CLIPS Working Memory:" crlf)
;;(facts)


;; Calling run function (Jess inference engine) to query the Working Memory via the rules in load-rules.clp
;; and find out which rules fire ... displaying data

;;(run)


;;Erase all Facts and Rules

(clear)
