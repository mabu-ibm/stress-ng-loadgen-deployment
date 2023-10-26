<?php
  $x = 0.0001;
  for ($i = 0; $i <= 1000000; $i++) {
    $x += sqrt($x);
  }
  echo "OK!";
  echo "<script>
  (function(s,t,a,n){s[t]||(s[t]=a,n=s[a]=function(){n.q.push(arguments)},
  n.q=[],n.v=2,n.l=1*new Date)})(window,"InstanaEumObject","ineum");

  ineum('reportingUrl', 'https://instana.fritz.box:446/eum/');
  ineum('key', 'SPmxh_n2RVOiUdGiOxljWw');
  ineum('trackSessions');
</script>
<script defer crossorigin="anonymous" src="https://instana.fritz.box:446/eum/eum.min.js"></script>";
?>
