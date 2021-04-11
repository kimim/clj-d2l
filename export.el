(setq org-publish-project-alist
      '(
        ("clj-d2l"
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
         :makeindex t
         :auto-sitemap t
         :html-link-up "sitemap.html"
         :html-link-home "index.html"
         :sitemap-title "My Sitemap"
         :exclude "sitemap.org"
         :with-drawers t
         )))

(setq  org-export-babel-evaluate nil)
