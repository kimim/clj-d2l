((nil . ((org-export-use-babel . nil)
         (org-html-head . "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\" />")
         (eval
          .
          (defun org-babel-expand-body:clojure (body params)
            "Expand BODY according to PARAMS, return the expanded body."
            (let* ((vars (org-babel--get-vars params))
	               (ns (or (cdr (assq :ns params))
		                   (org-babel-clojure-cider-current-ns)))
	               (result-params (cdr (assq :result-params params)))
	               (print-level nil)
	               (print-length nil)
	               (body (org-trim
		                  (concat
		                   ;; Source block specified namespace :ns.
		                   (and (cdr (assq :ns params)) (format "(ns %s)\n" ns))
		                   ;; Variables binding.
		                   (if (null vars) (org-trim body)
		                     (format "(let [%s]\n%s)"
			                         (mapconcat
			                          (lambda (var)
			                            (format "%S (quote %S)" (car var) (cdr var)))
			                          vars
			                          "\n      ")
			                         body))))))
              (if (or (member "code" result-params)
	                  (member "pp" result-params))
	              (format "(clojure.core/print (str (do %s)))" body)
                body))))
         (org-publish-project-alist
          .
          (("clj-d2l"
            ;; Path to your org files.
            :base-directory "./notes"
            :base-extension "org"
            :publishing-directory "./docs"
            :recursive t
            :publishing-function org-html-publish-to-html
            :headline-levels 4
            :section-numbers t
            :html-extension "html"
            :body-only nil
            :with-toc t
            :cache t
            :style "<link rel=\"stylesheet\" href=\"css/style.css\" type=\"text/css\"/>"
            :makeindex t
            :auto-sitemap t
            :html-link-up "sitemap.html"
            :html-link-home "index.html"
            :sitemap-title "My Sitemap"
            :exclude "sitemap.org"
            :with-drawers t
            ))))))
