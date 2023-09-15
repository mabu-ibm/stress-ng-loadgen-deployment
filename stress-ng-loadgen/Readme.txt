Pod with stress-ng Load Generator

Goal ist generate load, with pod limits so that pod reach the Limits and Turbonomic detect the bottleneck,
change limits with GitOPs 

we use the folling options :
       -c N, 
              start N workers  exercising  the  CPU  by  sequentially  working  through  all  the
              different CPU stress methods. Instead of exercising all the CPU stress methods, one
              can specify a specific CPU stress method with the --cpu-method option.

       -l P, --cpu-load P
              load CPU with P percent loading for the CPU stress  workers.  0  is  effectively  a
              sleep  (no  load) and 100 is full loading.  The loading loop is broken into compute
              time (load%) and sleep time (100% - load%). Accuracy depends on the overall load of
              the  processor  and  the responsiveness of the scheduler, so the actual load may be
              different from the desired load.  Note that the number of bogo CPU  operations  may
              not  be  linearly scaled with the load as some systems employ CPU frequency scaling
              and so heavier loads produce an  increased  CPU  frequency  and  greater  CPU  bogo
              operations.

