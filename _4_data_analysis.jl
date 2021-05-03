using JSON
##Vars
reports_dir = "ProcessedReports/"
##
function load_json(path) return JSON.parsefile(path) end

function process_reports(path) println(load_json(path)) end

for fname in readdir(reports_dir) process_reports(reports_dir * fname)  end

